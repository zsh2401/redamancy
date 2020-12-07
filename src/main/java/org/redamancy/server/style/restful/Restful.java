package org.redamancy.server.style.restful;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mark the specified controller method should be treated as Restful API.
 * <b>The type of return value of the marked method should be Object in any situation!</b>
 * <br/>The situation has been divided into two mainly.
 * <b>NO EXCEPTION</b>
 * If you method executed correctly, the value returned will be divided into two situations to deal with.
 * <ul>
 *     <li>Returned {@link RestfulResponse}: When you returns an RestfulResponse instance,
 *          it will be serialized directly without any further more handle and return to the API Caller.</li>
 *
 *     <li>Returned Any object expect {@link RestfulResponse}: The value will eventually
 *          be packed with {@link RestfulResponse} and returned to the API Caller.
 *     </li>
 * </ul>
 * <pre>
 * {@code
 * @Restful
 * private Object getAll(){
 *      return new int[]{1,2,3,4,5}
 * }
 *
 * @Restful
 * private Object test(){
 *    return JsonResponse.ok("Since I was young, I know I'd find you.");
 * }
 * }
 * </pre>
 *
 * <b>THROWS EXCEPTION</b>
 * Is there any possibility that method could be executed correctly under some special condition?
 * {@link RestfulHandler#handle(ProceedingJoinPoint, Restful)} also considered this situation.
 * the Exceptions you threw will be also caught and packed with {@link RestfulResponse} and return to the API Caller.
 * <ul>
 *     <li>Returned {@link org.redamancy.server.exception.RestfulException}: When you returns an RestfulResponse instance,
 *          it will be serialized directly without any further more handle and return to the API Caller.</li>
 *
 *     <li>Returned Any object expect {@link RestfulResponse}: The statusCode in RestfulResponse will be set to
 *          {@link javax.servlet.http.HttpServletResponse#SC_INTERNAL_SERVER_ERROR}, the message will be the value of {@link Exception#toString()}
 *          certain exception
 *     </li>
 * </ul>
 *
 * <pre>
 * {@code
 * @Restful
 * private Object getAll(){
 *      throw new RestfulException(404,"Not found",new NoSuchElementException());
 * }
 *
 * @Restful
 * private Object test(){
 *      throw new Exception();
 * }
 * }
 * </pre>
 *
 * @author zsh2401
 * @program serein
 * @create 2020-12-06 21:30
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Restful {
}
