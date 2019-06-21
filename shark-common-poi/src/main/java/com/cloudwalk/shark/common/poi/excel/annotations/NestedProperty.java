/**
 * 
 */
package com.cloudwalk.shark.common.poi.excel.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 
 * @author kevin
 *
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface NestedProperty {

}
