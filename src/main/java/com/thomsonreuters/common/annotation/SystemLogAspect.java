package com.thomsonreuters.common.annotation;

import org.apache.commons.lang3.StringEscapeUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.thomsonreuters.common.mapper.JsonMapper;
import com.thomsonreuters.modules.am.domain.User;
import com.thomsonreuters.modules.am.utils.UserUtils;

import javax.servlet.http.HttpServletRequest;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.UUID;

@Aspect
@Component
public class SystemLogAspect {

	private static final Logger logger = LoggerFactory
			.getLogger(SystemLogAspect.class);

	ThreadLocal<Long> time = new ThreadLocal<Long>();
	ThreadLocal<String> tag = new ThreadLocal<String>();

	// @Pointcut("@annotation(com.thomsonreuters.common.annotation.SystemControllerLog)")
	// public void controllerLog() {
	// }
	//
	// @Pointcut("@annotation(com.thomsonreuters.common.annotation.SystemServiceLog)")
	// public void serviceLog() {
	// }
	//
	// /**
	// * beforeExec
	// *
	// * @param joinPoint
	// */
	// @Before("controllerLog()")
	// public void beforeExec(JoinPoint joinPoint) {
	// System.err.println("-------------------->beforeExec");
	// time.set(System.currentTimeMillis());
	// tag.set(UUID.randomUUID().toString());
	// info(joinPoint);
	// MethodSignature ms = (MethodSignature) joinPoint.getSignature();
	// Method method = ms.getMethod();
	// System.err.println(method.getAnnotation(SystemServiceLog.class)
	// .description() + ". Tag: " + tag.get());
	// }
	//
	// /**
	// * afterExec
	// *
	// * @param joinPoint
	// */
	// @After("controllerLog()")
	// public void afterExec(JoinPoint joinPoint) {
	// System.err.println("-------------------->afterExec");
	// MethodSignature ms = (MethodSignature) joinPoint.getSignature();
	// Method method = ms.getMethod();
	// System.err.println("Tag: " + tag.get() + ", method: " + method.getName()
	// + " - running " + (System.currentTimeMillis() - time.get()) + "ms");
	// }
	//
	// /**
	// * aroundExec
	// *
	// * @param proceedingJoinPoint
	// */
	// @Around("controllerLog()")
	// public Object aroundExec(ProceedingJoinPoint pjp) throws Throwable {
	// System.err.println("-------------------->aroundExec");
	// return pjp.proceed();
	// }
	//
	private void info(JoinPoint joinPoint) {
		logger.debug("King: " + joinPoint.getKind());
		logger.debug("Target: " + joinPoint.getTarget().toString());
		Object[] os = joinPoint.getArgs();
		logger.debug("Args: ");
		for (int i = 0; i < os.length; i++) {
			logger.debug("==>Param[" + i + "]: "
					+ StringEscapeUtils.unescapeHtml4(os[i].toString()));
		}
		logger.debug("Signature: " + joinPoint.getSignature());
		logger.debug("SourceLocation: " + joinPoint.getSourceLocation());
		logger.debug("StaticPart: " + joinPoint.getStaticPart());
	}

	// Service Pointcut
	@Pointcut("@annotation(com.thomsonreuters.common.annotation.SystemServiceLog)")
	public void serviceAspect() {
	}

	// Controller Pointcut
	@Pointcut("@annotation(com.thomsonreuters.common.annotation.SystemControllerLog)")
	public void controllerAspect() {
	}

	/**
	 * doBefore
	 *
	 * @param joinPoint
	 */
	@Before("controllerAspect()")
	public void doBefore(JoinPoint joinPoint) {
		long curr = System.currentTimeMillis();
		time.set(curr);
		tag.set(UUID.randomUUID().toString());
		MethodSignature ms = (MethodSignature) joinPoint.getSignature();
		Method method = ms.getMethod();
		logger.debug("Tag: " + tag.get() + ", method: " + method.getName()
				+ " - started at: " + new Timestamp(curr));

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		User user = UserUtils.getUser();
		// IP
		String ip = request.getRemoteAddr();
		try {
			logger.debug("******** Controller doBefore ********");
			logger.debug("Method name: "
					+ (joinPoint.getTarget().getClass().getName() + "."
							+ joinPoint.getSignature().getName() + "()"));
			logger.debug("Method description: "
					+ getControllerMethodDescription(joinPoint));
			info(joinPoint);
			logger.debug("User: " + user.getUsername());
			logger.debug("IP: " + ip);
			logger.debug("******** Controller doBefore done ********");
		} catch (Exception e) {
			logger.error("******** Controller doBefore exception ********");
			logger.error("Exception: {}", e.getMessage());
		}
	}

	/**
	 * doAfter
	 *
	 * @param joinPoint
	 */
	@After("controllerAspect()")
	public void doAfter(JoinPoint joinPoint) {
		MethodSignature ms = (MethodSignature) joinPoint.getSignature();
		Method method = ms.getMethod();
		long curr = System.currentTimeMillis();
		logger.debug("Tag: " + tag.get() + ", method: " + method.getName()
				+ " - finished at " + new Timestamp(curr) + ", running "
				+ (curr - time.get()) + "ms");
	}

	/**
	 * doAfterThrowing
	 *
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		User user = UserUtils.getUser();
		// IP
		String ip = request.getRemoteAddr();
		String params = "";
		if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
			for (int i = 0; i < joinPoint.getArgs().length; i++) {
				params += JsonMapper.toJsonString(joinPoint.getArgs()[i]) + ";";
			}
		}
		try {
			logger.debug("******** Service exception notice ********");
			logger.debug("Exception name: " + e.getClass().getName());
			logger.debug("Exception message: " + e.getMessage());
			logger.debug("Method name: "
					+ (joinPoint.getTarget().getClass().getName() + "."
							+ joinPoint.getSignature().getName() + "()"));
			logger.debug("Method description: "
					+ getServiceMethodDescription(joinPoint));
			logger.debug("Method arguments: " + params);
			logger.debug("User: " + user.getUsername());
			logger.debug("IP: " + ip);
			System.err
					.println("******** Service exception notice done ********");
		} catch (Exception ex) {
			logger.error("******** Exception in Service exception notice ********");
			logger.error("Exception: {}", ex.getMessage());
		}
		logger.error(
				"Exception method: {}. Exception code: {}. Exception message: {}. Arguments: {}.",
				joinPoint.getTarget().getClass().getName()
						+ joinPoint.getSignature().getName(), e.getClass()
						.getName(), e.getMessage(), params);

	}

	/**
	 * getServiceMethodDescription
	 *
	 * @param joinPoint
	 * @return description
	 * @throws Exception
	 */
	public static String getServiceMethodDescription(JoinPoint joinPoint)
			throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(SystemServiceLog.class)
							.description();
					break;
				}
			}
		}
		return description;
	}

	/**
	 * getServiceMthodDescription
	 *
	 * @param joinPoint
	 * @return description
	 * @throws Exception
	 */
	public static String getControllerMethodDescription(JoinPoint joinPoint)
			throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(
							SystemControllerLog.class).description();
					break;
				}
			}
		}
		return description;
	}

}