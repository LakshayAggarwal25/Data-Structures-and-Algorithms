import java.util.HashSet;

/**
 * Leetcode 876  - Mid Node 
 * Leetcode 206  - Reverse 
 * Leetcode 143  - ReOrder
 * Leetcode 234  - Palindrome
 * Leetcode 21   - Merge Two List
 * Leetcode 19   - Remove nth From End
 * Leetcode 2    - Add two LL
 * Leetcode 445  - Add two LL (2)
 * https://nados.io/question/subtract-two-linkedlist?zen=true - Subtract two LL - tbd
 * Leetcode 83   - Delete Duplicates
 * Leetcode 328  - Odd Even List
 * Leetcode 148  - Sort List
 * Leetcode 23   - Merge K List
 * Leetcode 25   - Reverse K Group
 * Leetcode 92   - Reverse in Range - tbd
 * Leetcode 1171 - Zero Sum LL - tbd
 * Leetcode 138  - Copy List Random Pointer - tbd
 * Leetcode 817  - Components of LL
 * Leetcode 141  - Cycle in Linked List
 * Leetcode 142  - Detect Cycle Start
 * Leetcode 160  - Intersection of LL
 */


public class LinkedList {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    // finds the second mid in case of even size linked list
    public ListNode middleNode(ListNode head) {
        if(head == null || head.next==null) return head;
        ListNode fast = head;
        ListNode slow = head;
        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    // finds the first mid in case of odd size linked list
    public ListNode mid(ListNode head){
        if(head == null || head.next==null) return head;
        ListNode fast = head;
        ListNode slow = head;
        while(fast.next!=null && fast.next.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    // 206
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next==null) return head;
        ListNode prev = null;
        ListNode curr = head;
        while(curr!=null){
            ListNode forward = curr.next;
            curr.next = prev;
            prev = curr;
            curr = forward;
        } 
        head = prev;
        return head;
    }

    // 143
    public void reorderList(ListNode head) {
        if(head==null || head.next==null) return;
        ListNode c1 = head;
        ListNode mid = mid(head);
        ListNode c2 = mid.next;
        
        mid.next = null;

        c2 = reverseList(c2);

        ListNode f1 = c1.next;
        ListNode f2 = c2.next;

        while(true){
            c1.next = c2;
            c2.next = f1;
            
            c1 = f1;
            c2 = f2;
            
            if(c1!=null) 
                f1 = c1.next;
            else break;
           
            if(c2!=null)
                f2 = c2.next;
            else break;
        }

        return;
    }

    // 234
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return head==null?false:true;
        ListNode mid = mid(head);
        ListNode c1 = head, c2= mid.next;
        ListNode temp = c2;
        mid.next = null;
        c2 = reverseList(c2);
        
        boolean flag = true;
        
        while(c2!=null){
            if(c1.val!=c2.val){
                flag = false;
                break;
            }
            c1 = c1.next;
            c2 = c2.next;
        }

        temp = reverseList(temp);
        mid.next=temp;
        return flag;        
    }

    // 21
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 ==null) return list2;
        if(list2==null) return list1;

        ListNode temp = new ListNode(-1);
        ListNode head = temp;
        while(list1!=null && list2!=null){
            if(list1.val<list2.val) {
                temp.next = list1;
                list1 = list1.next;
            }
            else {
                temp.next = list2;
                list2 = list2.next;
            }

            temp=temp.next;
        }

        if(list1!=null) temp.next = list1;
        if(list2!=null) temp.next = list2;

        ListNode rv = head.next;
        head.next = null;
        return rv;
    }

    // 19
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null) return head;
        int i = 0;
        ListNode fast = head, slow = head;
        while(i<n){
            i++;
            fast = fast.next;
        }

        if(fast == null){
            ListNode temp = head.next;
            head.next = null;
            head = temp;
            return head;
        }

        while(fast.next!=null){
            slow = slow.next;
            fast = fast.next;
        }

        ListNode temp = slow.next;
        slow.next = temp.next;
        temp.next = null;
        return head;
    }

    // 2
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(-1);
        ListNode temp = ans;
        int carry = 0;
        while(l1!=null || l2!=null|| carry>0){
            int sum = ( l1!=null?l1.val:0 ) + (l2!=null?l2.val:0) + carry;
            carry = sum/10;
            sum = sum%10;
            ans.next = new ListNode(sum);
            ans = ans.next; 
            if(l1!=null) l1 = l1.next;
            if(l2!=null) l2 = l2.next;

        }
        ans = temp.next;
        temp.next = null;
        return ans;
    }
  
    // 445
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(-1);
        ListNode temp = ans;
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        int carry = 0;
        while(l1!=null || l2!=null|| carry>0){
            int sum = ( l1!=null?l1.val:0 ) + (l2!=null?l2.val:0) + carry;
            carry = sum/10;
            sum = sum%10;
            ans.next = new ListNode(sum);
            ans = ans.next; 
            if(l1!=null) l1 = l1.next;
            if(l2!=null) l2 = l2.next;

        }
        ans = temp.next;
        temp.next = null;
        ans = reverseList(ans);
        return ans;
    }
    
    // 83
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode prev = head, curr = head.next;

        while (curr != null) {
            if (prev.val != curr.val) {
                prev.next = curr;
                prev = prev.next;
            }
            curr = curr.next;
        }
        prev.next = curr;

        return head;
    }

    // 328
    public ListNode oddEvenList(ListNode head) {
        if(head==null || head.next == null) return head;
        ListNode odd = new ListNode(-1);
        ListNode even = new ListNode(-1);
        ListNode tempEvenHead = even,tempOddHead = odd;
        int i = 0;
        ListNode temp = head;
        while(temp!=null){
            if((i&1)==0){
                even.next = new ListNode(temp.val);
                even = even.next;
            }
            else{
                odd.next = new ListNode(temp.val);
                odd = odd.next;
            }

            temp = temp.next;
            i++;
        }
        head = tempEvenHead.next;
        tempEvenHead.next = null;
        even = tempOddHead.next;
        return head;
    }

    // 148
    public ListNode sortList(ListNode head) {
        if(head == null || head.next==null) return head;
        ListNode mid = mid(head);
        ListNode nHead = mid.next;
        mid.next=null;
        return mergeTwoLists(sortList(head),sortList(nHead));
    }

    // 23
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0) return null;
        
        ListNode head = new ListNode(-1), tail = head;
        for(ListNode list : lists){
            ListNode tempHead = list;
            if(tempHead==null) continue;
            tail.next = tempHead;
            while(tempHead.next!=null){
                tempHead = tempHead.next;
            }
            tail = tempHead;
        }
        tail = head;
        head = head.next;
        tail.next = null;
        return sortList(head);
    }

    // 25
    public void addFirst(ListNode node, ListNode[] pos){
        if(pos[0]==null){
            pos[0] = node;
            pos[1] = pos[0];
        }else{
            node.next = pos[0];
            pos[0] = node;
        }
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next==null || k==1) return head;
        ListNode temp = head;
        int len = 0;
        while(temp!=null){
            temp = temp.next;
            len++;
        }
        temp = head;
        
        ListNode[] pos = {null,null};
        ListNode nHead=null,tail=null;
        
        while(len >= k){
            int tempK = k;
            while(tempK-- > 0){
                ListNode fow = temp.next;   
                temp.next = null;
                addFirst(temp,pos);
                temp = fow;
            }
            
            if(nHead == null){
                nHead = pos[0];
                tail = pos[1];
            }
            else{
                tail.next = pos[0];
                tail = pos[1];
            }
            
            pos[0] = pos[1] = null;
            
            len-=k;
        }
        
        tail.next = temp;
        return nHead;
    }

    // 817
    public int numComponents(ListNode head, int[] nums) {
        if(head==null || nums.length==0) return 0;
        
        HashSet<Integer> set = new HashSet<>();
        for(int x : nums) set.add(x);
        
        int count = 0;
        while(head!=null){
            if(set.contains(head.val) && (head.next==null || !set.contains(head.next.val))){
                count++;
            }
            head= head.next;
        }
        return count;
    }

    // 141
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;
        ListNode slow = head, fast = head;
        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if( fast==slow) break;
        }

        return fast==slow;
    }

    // 142
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) return null;
        ListNode slow = head, fast = head;
        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if( fast==slow) break;
        }
        if(fast!=slow) return null;
        slow = head;
        while(fast!=slow){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    // 160
    private ListNode getTail(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }

        return curr;
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;

        ListNode tail = getTail(headA);
        tail.next = headB;
        ListNode intersectionNode = detectCycle(headA);
        tail.next = null;  

        return intersectionNode;
    }
}