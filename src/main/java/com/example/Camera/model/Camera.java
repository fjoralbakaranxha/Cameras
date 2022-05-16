package com.example.Camera.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document

public class Camera {
    @Id
    private String id;

    @NotNull(message = "Name cannot be null")
    private String name;
    @NotNull(message = "Model cannot be null")
    private String model;
    @NotNull(message = "Resolution cannot be null")
    private String resolution;
    @NotNull(message = "IP cannot be null")
    private String ip;
}
