package com.ntahr.common.dataaccess.objects;

import javax.persistence.*;

@Entity
@Table(name = "devicemaster") // @Table is optional
public class DeviceModel {

    @Id // @Id indicates that this it a unique primary key
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long modelId;

    @Column(length = 100)
    private String modelName;

    @Column(length = 100)
    private String modelType;

    @Column(length = 1)
    private Boolean modelEnabled;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public Boolean getModelEnabled() {
        return modelEnabled;
    }

    public void setModelEnabled(Boolean modelEnabled) {
        this.modelEnabled = modelEnabled;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }
}
