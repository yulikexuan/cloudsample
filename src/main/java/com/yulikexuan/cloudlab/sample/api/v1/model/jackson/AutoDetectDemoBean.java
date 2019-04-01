//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.AutoDetectDemoBean.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;


/*
 * The @JsonAutoDetect annotation is used at the class level to tell Jackson to
 * override the visibility of the properties of a class during serialization and
 * deserialization
 *
 * By default Jackson can access public fields for serialization and deserialization.
 * If there's no public fields available then public getters/setters are used.
 *
 * This behavior can be customized by the use of @JsonAutoDetect annotation.
 * @JsonAutoDetect annotation can be used to specify access visibility rules for
 * fields and/or methods.
 *
 * The visibilities that can be set: with the following elements:
 *   - creatorVisibility
 *   - fieldVisibility
 *   - getterVisibility
 *   - setterVisibility
 *   - isGetterVisibility
 *
 * The JsonAutoDetect class defines public static constants that are similar to
 * Java class visibility levels. They are:
 *   - ANY: All access modifiers (including private) of the 'fields' can be detectable.
 *   - DEFAULT:
 *   - NON_PRIVATE
 *   - NONE
 *   - PROTECTED_AND_PRIVATE
 *   - PUBLIC_ONLY
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Builder @AllArgsConstructor
public class AutoDetectDemoBean {

    @Builder.Default private long personId = 123L;
    @Builder.Default private String name = "James Clark";

}///:~