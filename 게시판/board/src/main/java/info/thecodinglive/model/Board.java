package info.thecodinglive.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name="board")
public class Board implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="board_id")
	private int id;
	private String uname;
	private String title;
	private String catagory;
	private String content;
	private int viewcount;
	private Date wdate;
	@PrePersist
	public void beforeCreate() {
		wdate=new Date();
	}
	

}
