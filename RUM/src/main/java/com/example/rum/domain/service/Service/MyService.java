package com.example.rum.domain.service.Service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.example.rum.domain.dto.MyDto;
import com.example.rum.domain.dto.MyDtoUpdate;
import com.example.rum.domain.entity.MyEntity;


@Service
public interface MyService {

	
	 /**
	  * ユーザー情報 全検索
	  * @return 検索結果
	  */
	 public List<MyEntity> searchAll();
	 
		
	 /**
	* ユーザー情報 主キー検索
	* @return  検索結果
	*/
	public MyEntity findById(Integer id); 

	 /**
	  * ユーザー情報 新規登録
	  * @param user ユーザー情報
	  */
	 public void create(MyDto myDto);
	 
	/**
	 * ユーザー情報 更新
	* @param  user ユーザー情報
	*/
	public void update(MyDtoUpdate myDtoUpdate); 
	
	/**
	 * ユーザー情報 物理削除
	 * @param  id ユーザーID
	 */
	public void delete(Integer id);
}
