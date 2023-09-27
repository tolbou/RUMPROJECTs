package com.example.rum.domain.entity;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * ユーザー情報 Entity
 */
@Data
@Entity
@Table(name = "user", schema = "public")
public class MyEntity  {

 /**
  * ID
  */
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "id")
 private Integer id;

 /**
  * 名前
  */
 @Column(name = "name")
 private String name;

 /**
  * 住所
  */
 @Column(name = "address")
 private String address;

 /**
  * 電話番号
  */
 @Column(name = "phone")
 private String phone;

 /**
  * 更新日時
  */
 @Column(name = "update_date")
 private Date updateDate;

 /**
  * 登録日時
  */
 @Column(name = "create_date")
 private Date createDate;
}