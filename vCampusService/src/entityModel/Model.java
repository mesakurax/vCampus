package entityModel;
/**
 * 数据库操作（Model）抽象接口
 *
 *
 */
public abstract interface Model {
    boolean Insert(Object obj);

    boolean Modify(Object obj);

    boolean Delete(Object obj);

    Object Search(Object obj, int opt);
}