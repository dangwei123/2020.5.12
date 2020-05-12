要么让所有奇数下标都是奇数，要么让所有偶数下标都是偶数
public class Modified {
    public void modified(int[] arr){
        int end=arr.length-1;
        int before=0;
        int after=1;
        while(before<=end&&after<=end){
            if((arr[end]&1)==0){
                swap(arr,before,end);
                before+=2;
            }else{
                swap(arr,after,end);
                after+=2;
            }
        }
    }


最大子序和
public int maxSubArray(int[] nums) {
        if(nums.length<1) return 0;
        int max=nums[0];
        int sum=nums[0];
        for(int i=1;i<nums.length;i++){
            if(sum>=0){
                sum+=nums[i];
            }else{
                sum=nums[i];
            }
            max=Math.max(max,sum);
        }
        return max;
    }
	
	子矩阵最大和
	public class MaxSumOfMatrix {
    public int maxSumOfMatrix(int[][] matrix){
         int res=Integer.MIN_VALUE;
         for(int i=0;i<matrix.length;i++){
             int[] cur=new int[matrix[0].length];
             for(int j=i;j<matrix.length;j++){
                 int num=0;
                 for(int k=0;k<matrix[j].length;k++){
                     cur[k]+=matrix[j][k];
                     num+=cur[k];
                     res=Math.max(res,num);
                     if(num<0) num=0;
                 }
             }
         }
         return res;
    }
}


波谷的位置
public class GetLessIndex {
    public int getLessIndex(int[] arr){
        if(arr.length==0) return -1;
        if(arr.length==1||arr[0]<arr[1]) return 0;
        if(arr[arr.length-1]<arr[arr.length-2]) return arr.length-1;
        int left=0;
        int right=arr.length-1;
        while(left<right){
            int mid=left+(right-left)/2;
            if(arr[mid]>arr[mid+1]){
                left=mid+1;
            }else{
                right=mid;
            }
        }
        return left;
    }
}

乘积最大子数组
public int maxProduct(int[] nums) {
        int max=nums[0];
        int min=nums[0];
        int res=nums[0];
        for(int i=1;i<nums.length;i++){
            int pre=max;
            max=Math.max(nums[i],Math.max(max*nums[i],min*nums[i]));
            min=Math.min(nums[i],Math.min(pre*nums[i],min*nums[i]));
            res=Math.max(res,max);
        }
        return res;
    }
	
	按从大到小的顺序打印N个数组中前k大的数
public class PrintTopK {
    private static class Node{
        int val;
        int row;
        int col;
        public Node(int val,int row,int col){
            this.val=val;
            this.row=row;
            this.col=col;
        }
    }
   public List<Integer> printTOPK(int[][] arr,int k){
        List<Integer> res=new ArrayList<>();
        PriorityQueue<Node> pq=new PriorityQueue<>((o1,o2)->o2.val-o1.val);
        for(int i=0;i<arr.length;i++){
            int j=arr[i].length-1;
            Node node=new Node(arr[i][j],i,j);
            pq.offer(node);
        }
        while(k--!=0&&!pq.isEmpty()){
            Node node=pq.poll();
            res.add(node.val);
            if(node.col!=0){
                node.val=arr[node.row][node.col-1];
                node.col-=1;
                pq.offer(node);
            }
        }
        return res;
    }
}

边界都是1的最大正方形的边长
public class EdgeIsOne {
    public int getEdgOne(int[][] arr){
        int row=arr.length;
        int col=arr[0].length;
        int[][] right=new int[row][col];
        int[][] down=new int[row][col];
        fill(arr,right,down);
        for(int i=Math.min(row,col);i>=0;i--){
            if(isValid(right,down,i)) return i;
        }
        return 0;
    }
    private boolean isValid(int[][] right,int[][] down,int size){
        for(int i=0;i<right.length-size;i++){
            for(int j=0;j<right[0].length-size;j++){
                if(right[i][j]>=size&&down[i][j]>=size
                   &&right[i+size-1][j]>=size&&down[i][j+size-1]>=size){
                    return true;
                }
            }
        }
        return false;
    }
    private  void fill(int[][] arr,int[][] right,int[][] down){
        int row=arr.length;
        int col=arr[0].length;
        if(arr[row-1][col-1]==1){
            right[row-1][col-1]=1;
            down[row-1][col-1]=1;
        }
        //最后一列
        for(int i=row-2;i>=0;i--){
            if(arr[i][col-1]==1){
                right[i][col-1]=1;
                down[i][col-1]=right[i+1][col-1]+1;
            }
        }
        //最后一行
        for(int i=col-2;i>=0;i--){
            if(arr[row-1][i]==1){
                right[row-1][i]=right[row-1][i+1]+1;
                down[row-1][i]=1;
            }
        }

        for(int i=row-2;i>=0;i--){
            for(int j=col-2;j>=0;j--){
                if(arr[i][j]==1){
                    right[i][j]=right[i][j+1]+1;
                    down[i][j]=down[i+1][j]+1;
                }
            }
        }
    }
}

