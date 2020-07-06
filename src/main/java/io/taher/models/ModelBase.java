package io.taher.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ModelBase implements Serializable {

	private static final long serialVersionUID = -7217052688240896188L;
	ModelBase(int id){
		this.id = id;
	}
	ModelBase(){}
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="seq")
	@GenericGenerator(name = "seq", strategy="increment")
    private int id;

    @Column(name = "created_at", nullable = false , updatable = false)
    @CreationTimestamp
    private Date created_at;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updated_at;
    
    @Column(name = "deleted", columnDefinition = "Bit(1) default false")
    private boolean deleted = false;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
    
	
    public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	@PrePersist
    protected void prePersist() {
        if (this.created_at == null) created_at = new Date();
        if (this.updated_at == null) updated_at = new Date();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updated_at = new Date();
    }
    

}