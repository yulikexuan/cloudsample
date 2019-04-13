//: com.yulikexuan.cloudlab.sample.api.v1.model.CustomerDTO.java


package com.yulikexuan.cloudlab.sample.api.v1.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Size;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Builder @AllArgsConstructor
public class CustomerDTO {

    @Size(min = 2)
    @ApiModelProperty(value = "The first name of this customer.", required = true)
    private String firstname;

    @Size(min = 2)
    @ApiModelProperty(value = "The last name of this customer.", required = true)
    private String lastname;

    @ApiModelProperty(value = "The URL for this customer.", required = true)
    private String customerUrl;

}///:~