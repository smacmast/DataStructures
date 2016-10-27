/**
 * Contains a data value and a link to left and right TreeNode to be used in a binary tree data structure
 * @author: Spencer MacMaster
 */
public class TreeNode<T> {
	public T data;
	public TreeNode<T> left, right;
	
	TreeNode(T data){
		this.data = data;
		this.left = null; this.right = null;
	}
}