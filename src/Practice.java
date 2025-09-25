import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Practice {
    /**
     * Returns the sum of the odd numbers in the array.
     * 
     * Returns 0 if the array is null or has no odd numbers.
     * 
     * @param nums an array of numbers
     * @return the sum of the odd numbers in the array
     */
    public static int oddSum(int[] nums) {
        if (nums==null||nums.length==0) return 0;
        int sum = 0;
        for (int i: nums) {
            if (i%2==1||i%2==-1) sum+= i;
        }
        return sum;
    }

    /**
     * Returns the shortest word in the Set.
     * 
     * If multiple words are tied for shortest, returns the one that is smallest
     * lexicographically.
     * 
     * @param words a set of words
     * @return the shortest word in the set with a lexicographic tiebreaker
     * @throws IllegalArgumentException if words is empty
     * @throws NullPointerException if words is null
     */
    public static String shortestWord(Set<String> words) {
        if (words==null) throw new NullPointerException();
        if (words.size()==0) throw new IllegalArgumentException();
        int shortestlength = Integer.MAX_VALUE;
        String shortest = "";
        for (String word : words) {
            if (word.length()<shortestlength) {
                shortest = word;
                shortestlength = word.length();
            }
            if (word.length()==shortestlength) {
                if(word.compareTo(shortest)<0) shortest = word;
            }
        }
        return shortest;
    }

    /**
     * Returns a set of all the names of people that are 18 years of age or older.
     * 
     * The input maps name to age in years.
     * 
     * @param ages mapping of name to age
     * @return the set of all names of people >= 18 years old
     * @throws NullPointerException if ages is null
     */
    public static Set<String> adults(Map<String, Integer> ages) {
        if (ages==null) throw new NullPointerException();
        Set<String> majors = new HashSet<>();
        for (String name : ages.keySet()) {
            if (ages.get(name)>=18) majors.add(name);
        }
        return majors;
    }

    /**
     * Returns the biggest number in a linked list.
     * 
     * @param head the head of the linked list
     * @return the biggest number in the list
     * @throws IllegalArgumentException if head is null
     */
    public static int biggestNumber(ListNode<Integer> head) {
        if (head==null) throw new IllegalArgumentException();
        int biggest = Integer.MIN_VALUE;
        while (head!=null) {
            if (head.data>biggest) biggest = head.data;
            head = head.next;
        }
        return biggest;
    }

    /**
     * Returns a frequency map counting how frequently items appear in a linked list.
     * 
     * Example:
     *   Input: a -> x -> a -> a -> x -> y
     *   Output: {a:3, x:2, y: 1}
     * 
     * Returns an empty map if head is null
     * 
     * @param <T> the type of data held by the list
     * @param head the head of the list
     * @return a frequency map of values in the list
     */
    public static <T> Map<T, Integer> frequencies(ListNode<T> head) {
        if (head==null) return Map.of();
        Map<T, Integer> frequency = new HashMap<>();
        while(head!=null) {
            if (frequency.containsKey(head.data)) {
                frequency.put(head.data, frequency.get(head.data)+1);
            }
            else {
                frequency.put(head.data, 1);
            }
            head=head.next;
        }
        return frequency;
    }


    /**
     * Returns the number of levels in the tree.
     * 
     * An empty tree has 0 levels, a tree with only a root has 1 level.
     * 
     * @param root the root of the tree
     * @return the number of levels in the tree
     */
    public static int levelCount(BinaryTreeNode<?> root) {
        if (root==null) return 0;
        int left = levelCount(root.left);
        int right = levelCount(root.right);
        return Math.max(left,right)+1;
    }


    /**
     * Returns the sum at a specified level in a binary tree.
     * 
     * For example, if the given level was 3:
     *       5
     *     /   \
     *    8     4
     *   / \   / 
     *  7  9  2
     *    /
     *   1
     * 
     * Nodes at level 3: 7, 9, and 2
     * Sum of nodes at level 3: 18 
     * 
     * The root is considered to be at level 1.
     * 
     * Returns 0 if the tree is empty or if the level is not present in the tree.
     * 
     * @param root the root of the binary tree
     * @param level the level to sum
     * @return the sum of the nodes at the given level
     */
    public static int sumAtLevel(BinaryTreeNode<Integer> root, int level) {
        if (root==null) return 0;
        Queue<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        queue.offer(root);
        int currlevel = 1;
        int levelsum = 0;
        while (!queue.isEmpty()) {
            int levelsize = queue.size();
            if (currlevel == level) {
                for (int i = 0; i < levelsize; i++) {
                    BinaryTreeNode<Integer> node = queue.poll();
                    levelsum += node.data;
                }
                return levelsum;
            }
            else {
                for (int j = 0; j < levelsize; j++) {
                    BinaryTreeNode<Integer> node = queue.poll();
                    if (node.left!=null) {
                        queue.offer(node.left);
                    }
                    if (node.right!=null) {
                        queue.offer(node.right);
                    }
                }
                currlevel++;
            }
        }
        return 0;
    }


    /**
     * Returns true if the sum of the values in a given tree is equal to the sum
     * of the values in the given list. 
     * 
     * An empty tree or list is considered to have a sum of 0.
     * 
     * @param root The root of the binary tree
     * @param head The head of the linked list
     * @return true if the sums are equal, false otherwise
     */
    public static boolean sumMatch(BinaryTreeNode<Integer> root, ListNode<Integer> head) {
        if (root==null&&head==null) return true;
        if (root==null||head==null) return false;
        Queue<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        int treesum = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode<Integer> node = queue.poll();
            if (node.left!=null) queue.offer(node.left);
            if (node.right!=null) queue.offer(node.right);
            treesum+=node.data;
        }
        int listsum = 0;
        while(head!=null) {
            listsum+= head.data;
            head=head.next;
        }
        return treesum==listsum;
    }

    /**
     * Returns the sum of all the vertices in a graph that are reachable from a given
     * starting vertex.
     * 
     * Returns 0 if the starting vertex is null.
     * 
     * @param start the starting vertex
     * @return the sum of all the vertices
     */
    public static int graphSum(Vertex<Integer> start) {
        HashSet<Vertex<Integer>> visited = new HashSet<Vertex<Integer>>();
        return graphSumHelper(start, visited);
    }
    public static int graphSumHelper(Vertex<Integer> start, Set<Vertex<Integer>> visited) {
        if (start==null||visited.contains(start)) return 0;
        visited.add(start);
        int sum = start.data;
        for (Vertex<Integer> neighbour : start.neighbors) {
            sum += graphSumHelper(neighbour, visited);
        }
        return sum;
    }

    /**
     * Returns the count of vertices in a graph that have an outdegree of 0.
     * 
     * Returns 0 if the starting vertex is null.
     * 
     * @param start the entrypoint to the graph
     * @return the count of vertices with outdegree 0
     */
    public static int sinkCount(Vertex<Integer> start) {
        HashSet<Vertex<Integer>> visited = new HashSet<>();
        return sinkCountHelper(start, visited);
    }
    public static int sinkCountHelper(Vertex<Integer> start, Set<Vertex<Integer>> visited) {
        if (start==null||visited.contains(start)) return 0;
        int sinkcount = 0;
        visited.add(start);
        if (start.neighbors.size()==0) sinkcount++;
        for (Vertex<Integer> neighbour : start.neighbors) {
            sinkcount += sinkCountHelper(neighbour, visited);
        }
        return sinkcount;
    }
}