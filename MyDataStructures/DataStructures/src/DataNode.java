/**
 * @author: Spencer MacMaster
 * @description: contains a data value and a link to next DataNode to be used in a data structure
 */
public class DataNode<T>{
	public T data;
	public DataNode<T> next;
	public DataNode<T> prev;
	
	public DataNode(T data) {
		this.data = data;
		this.next = null;
		this.prev = null;
	}
}
