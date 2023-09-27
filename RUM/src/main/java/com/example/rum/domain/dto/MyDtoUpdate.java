package com.example.rum.domain.dto;



import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ユーザー情報更新リクエストデータ
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MyDtoUpdate extends MyDto implements Serializable {

  /**
   * ユーザーID
   */
  @NotNull
  private Integer id;
}