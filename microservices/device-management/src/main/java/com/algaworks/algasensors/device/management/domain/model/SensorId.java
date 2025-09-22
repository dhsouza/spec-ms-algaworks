package com.algaworks.algasensors.device.management.domain.model;

import io.hypersistence.tsid.TSID;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SensorId implements Serializable {

    public static final String TSID_CANNOT_BE_NULL = "TSID cannot be null";
    private TSID value;

    public SensorId(TSID value) {
        Objects.requireNonNull(value, TSID_CANNOT_BE_NULL);
        this.value = value;
    }

    public SensorId(Long value) {
        Objects.requireNonNull(value, TSID_CANNOT_BE_NULL);
        this.value = TSID.from(value);
    }

    public SensorId(String value) {
        Objects.requireNonNull(value, TSID_CANNOT_BE_NULL);
        this.value = TSID.from(value);
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
