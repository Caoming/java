package com.felix.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author caoming
 * @Date: 2020/9/14 10:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GasInfo implements Serializable {
    private static final long serialVersionUID = -7137630374434403400L;

    private String gasName;

    private String gasId;

    private String gasCode;
}
