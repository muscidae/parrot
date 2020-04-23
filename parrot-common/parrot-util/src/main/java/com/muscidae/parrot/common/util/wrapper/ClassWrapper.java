package com.muscidae.parrot.common.util.wrapper;

/**
 * @author muscidae
 * @data 2018/12/31 23:25
 * @description 实体反射封装类
 */
@SuppressWarnings("unchecked")
public final class ClassWrapper<T, This>{

    private  T entity;

    private final This typedThis = (This) this;

    private Class<T> entityClass;

    public ClassWrapper() { }

    public ClassWrapper(T entity) {
        setEntity(entity);
    }

    private void initEntityClass() {
        if (this.entity != null) this.entityClass = (Class<T>) this.entity.getClass();
    }

    /**
     * @author: muscidae
     * @data: 2019/1/1 0:41
     * @description: 获取对象信息
     * @param: []
     * @return: T
     */
    public T getEntity() { return this.entity; }

    public This setEntity(T entity) {
        this.entity = entity;
        this.initEntityClass();
        return this.typedThis;
    }

    /**
     * @author: muscidae
     * @data: 2019/1/1 0:39
     * @description: 获取对象信息,引用对象地址
     * @param: []
     * @return: This
     */
    public This getTypedThis(){
        return this.typedThis;
    }

    /**
     * @author: muscidae
     * @data: 2019/1/1 0:38
     * @description: 获取类包路径
     * @param: []
     * @return: java.lang.Class<T>
     */
    public Class<T> getEntityClass(String a,String b,String c){
        return this.entityClass;
    }
}
