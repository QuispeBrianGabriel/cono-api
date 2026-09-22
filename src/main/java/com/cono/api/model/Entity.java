package com.cono.api.model;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@SuperBuilder
@NoArgsConstructor
public abstract class Entity {
  @Id private UUID id;

  @CreatedDate private LocalDateTime created;

  @LastModifiedDate private LocalDateTime updated;
}
