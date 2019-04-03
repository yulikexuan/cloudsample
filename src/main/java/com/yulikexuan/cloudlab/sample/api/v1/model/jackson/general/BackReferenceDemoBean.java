//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.general.BackReferenceDemoBean.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.general;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class BackReferenceDemoBean {

    @JsonProperty("Id")
    private long personId = 17L;

    @JsonProperty("Name")
    private String name = "Bill Gates";

    /*
     * The field marked with @JsonBackReference is the back reference and is
     * usually omitted during serialization
     */
    @JsonBackReference
    @JsonProperty("Employees")
    public List<ManagedReferenceDemoBean> employees;

    @Builder
    public BackReferenceDemoBean(long personId, String name) {
        this.personId = personId;
        this.name = name;
        this.employees = new ArrayList<>();
    }

    public void addEmployees(ManagedReferenceDemoBean managedReferenceDemoBean) {
        this.employees.add(managedReferenceDemoBean);
    }

}///:~