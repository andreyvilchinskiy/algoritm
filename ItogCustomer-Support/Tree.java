import java.util.*;

// Бинарное дерево поиска по id
public class Tree {
    TreeNode root;

    // true — добавлено, false — дубликат
    public boolean insert(Request r) {
        if (root == null) {
            root = new TreeNode(r); return true;
        }
        TreeNode cur = root;
        while (true) {
            if (r.id < cur.data.id) {

                if (cur.left == null)  {
                    cur.left  = new TreeNode(r);
                    return true;
                }
                cur = cur.left;

            } else if (r.id > cur.data.id) {

                if (cur.right == null) {
                    cur.right = new TreeNode(r);
                    return true;
                }
                cur = cur.right;

            } else {
                return false;
            }
        }
    }

    // Обходы

    public List<Request> preOrder()   {
        var out = new ArrayList<Request>();
        pre(root, out);
        return out;
    }

    public List<Request> inOrder()    {
        var out = new ArrayList<Request>();
        in(root, out);
        return out;
    }

    public List<Request> postOrder()  {
        var out = new ArrayList<Request>();
        post(root, out);
        return out;
    }

    private void pre (TreeNode n, List<Request> o) {
        if (n == null) {
            return;
        }
        o.add(n.data);
        pre(n.left,o);
        pre(n.right,o);
    }
    private void in  (TreeNode n, List<Request> o) {
        if (n == null){
            return;
        }
        in(n.left,o);
        o.add(n.data);
        in(n.right,o);
    }
    private void post(TreeNode n, List<Request> o) {
        if (n == null){
            return;
        }
        post(n.left,o);
        post(n.right,o);
        o.add(n.data);
    }

    public List<Request> levelOrder() {
        var out = new ArrayList<Request>();
        if (root == null){
            return out;
        }
        var q = new ArrayDeque<TreeNode>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode n = q.poll();
            out.add(n.data);
            if (n.left  != null){
                q.add(n.left);
            }
            if (n.right != null){
                q.add(n.right);
            }
        }
        return out;
    }

    // Поиск и характеристики

    public Object[] search(int id) {
        var path = new ArrayList<Integer>();
        TreeNode cur = root;
        while (cur != null) {
            path.add(cur.data.id);
            if (id == cur.data.id){
                return new Object[]{cur.data, path};
            }
            cur = (id < cur.data.id) ? cur.left : cur.right;
        }
        return new Object[]{null, path};
    }

    public Request findMin() {
        if (root == null){
            return null;
        }
        TreeNode n = root;
        while (n.left  != null){
            n = n.left;
        }
        return n.data;
    }

    public Request findMax() {
        if (root == null){
            return null;
        }
        TreeNode n = root;
        while (n.right != null){
            n = n.right;
        }
        return n.data;
    }

    public int countNodes()  {
        return nodes(root);
    }

    public int countLeaves() {
        return leaves(root);
    }

    public int height()      {
        return h(root);
    }

    private int nodes (TreeNode n) {
        return n == null ? 0 : 1 + nodes(n.left) + nodes(n.right);
    }

    private int leaves(TreeNode n) {
        if (n == null){
            return 0;
        }
        if (n.left == null && n.right == null){
            return 1;
        }
        return leaves(n.left) + leaves(n.right);
    }

    private int h(TreeNode n) {
        return n == null ? 0 : 1 + Math.max(h(n.left), h(n.right));
    }

    public int getDepth(int id) {
        TreeNode cur = root;
        int d = 0;
        while (cur != null) {
            if (id == cur.data.id) {
                return d;
            }
            cur = (id < cur.data.id) ? cur.left : cur.right;
            d++;
        }
        return -1;
    }

    // Полная проверка BST через диапазон (min, max)
    public boolean validateBst() {
        return valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean valid(TreeNode n, long min, long max) {
        if (n == null){
            return true;
        }
        long id = n.data.id;
        if (id <= min || id >= max){
            return false;
        }
        return valid(n.left, min, id) && valid(n.right, id, max);
    }

    // Удаление

    // true — удалено, false — не найдено
    public boolean delete(int id) {
        if (search(id)[0] == null){
            return false;
        }
        root = del(root, id);
        return true;
    }

    private TreeNode del(TreeNode n, int id) {
        if (n == null){
            return null;
        }
        if (id < n.data.id){
            n.left  = del(n.left,  id);
        }

        else if (id > n.data.id) {
            n.right = del(n.right, id);
        }

        else {
            if (n.left  == null){
                return n.right;
            }
            if (n.right == null){
                return n.left;
            }
            TreeNode succ = n.right;
            while (succ.left != null){
                succ = succ.left;
            }
            n.data  = succ.data;
            n.right = del(n.right, succ.data.id);
        }
        return n;
    }

    // Печать уровней

    public void printLevels() {
        if (root == null) {
            System.out.println("(пусто)");
            return;
        }
        var q = new ArrayDeque<TreeNode>();
        q.add(root);
        int lvl = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            var sb = new StringBuilder("Уровень " + lvl + ": ");
            for (int i = 0; i < size; i++) {
                TreeNode n = q.poll();
                sb.append(n.data);
                if (i < size - 1){
                    sb.append(", ");
                }
                if (n.left  != null){
                    q.add(n.left);
                }
                if (n.right != null){
                    q.add(n.right);
                }
            }
            System.out.println(sb);
            lvl++;
        }
    }
}