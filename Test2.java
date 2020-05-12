不包含本位置的累乘数组
public class Product {
    public int[] product(int[] arr){
        int[] res=new int[arr.length];
        res[0]=arr[0];
        for(int i=1;i<arr.length;i++){
            res[i]=res[i-1]*arr[i];
        }
        int tmp=1;
        for(int i=arr.length-1;i>0;i--){
            res[i]=res[i-1]*tmp;
            tmp*=arr[i];
        }
        res[0]=tmp;
        return res;
    }
}

三指针问题
public class LeftUnique {
    public void leftUnique(int[] arr){
        int left=0;
        for(int right=1;right<arr.length;right++){
            if(arr[right]!=arr[left]){
                int tmp=arr[++left];
                arr[left]=arr[right];
                arr[right]=tmp;
            }
        }
    }

    public void sortColors(int[] nums) {
        int left=0;
        int right=nums.length-1;
        int cur=0;
        while(cur<=right){
            if(nums[cur]==0){
                swap(nums,left++,cur++);
            }else if(nums[cur]==2){
                swap(nums,cur,right--);
            }else{
                cur++;
            }
        }
    }
    private void swap(int[] nums,int left,int right){
        int tmp=nums[left];
        nums[left]=nums[right];
        nums[right]=tmp;
    }
}


最短通路值
public class MinPathValue {
    public int minpathVlaue(int[][] arr){
        int row=arr.length;
        if(row==0) return 0;
        int col=arr[0].length;
        if(arr[0][0]==0) return 0;
        Queue<int[]> queue=new LinkedList<>();
        int res=0;
        queue.offer(new int[]{0,0});
        arr[0][0]=0;
        while(!queue.isEmpty()){
            res++;
            int size=queue.size();
            while(size--!=0){
                int[] tmp=queue.poll();
                int x=tmp[0];
                int y=tmp[1];
                if(x==row-1&&y==col-1) return res;
                if(x<row-1&&arr[x+1][y]==1){
                    queue.offer(new int[]{x+1,y});
                    arr[x+1][y]=0;
                }
                if(y<col-1&&arr[x][y+1]==1){
                    queue.offer(new int[]{x,y+1});
                    arr[x][y+1]=0;
                }
                if(x>0&&arr[x-1][y]==1){
                    queue.offer(new int[]{x-1,y});
                    arr[x-1][y]=0;
                }
                if(y>0&&arr[x][y-1]==1){
                    queue.offer(new int[]{x,y-1});
                    arr[x][y-1]=0;
                }
            }
        }
        return 0;
    }
}


给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
public int firstMissingPositive(int[] nums) {
        int n=nums.length;
        for(int i=0;i<n;i++){
            while(nums[i]>0&&nums[i]<=n&&nums[i]-1!=i&&nums[nums[i]-1]!=nums[i]){
                swap(nums,nums[i]-1,i);
            }
        }
        for(int i=0;i<n;i++){
            if(nums[i]-1!=i) return i+1;
        }
        return n+1;
    }
    private void swap(int[] nums,int left,int right){
        int tmp=nums[left];
        nums[left]=nums[right];
        nums[right]=tmp;
    }
	
public class Main{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int n=sc.nextInt();
            //Map<String,Integer> first=new HashMap<>();
            //Map<String,Integer> second=new HashMap<>();
            int[] first=new int[3];
            int[] second=new int[3];
            int winNum=0;
            int loseNum=0;
            int m=n;
            while(m--!=0){
                String a=sc.next();
                String b=sc.next();
                if(a.equals("C")){
                    if(b.equals("J")){
                        winNum++;
                        first[0]++;
                    }else if(b.equals("B")){
                        loseNum++;
                        second[2]++;
                    }

                }else if(a.equals("J")){
                    if(b.equals("B")){
                        winNum++;
                        first[1]++;
                    }else if(b.equals("C")){
                        loseNum++;
                        second[0]++;
                    }
                }else if(a.equals("B")){
                    if(b.equals("C")){
                        winNum++;
                        first[2]++;
                    }else if(b.equals("J")){
                        loseNum++;
                        second[1]++;
                    }
                }
            }
            int ping=n-winNum-loseNum;
            System.out.println(winNum+" "+ping+" "+loseNum);
            System.out.println(loseNum+" "+ping+" "+winNum);
            int max1=Math.max(first[0],Math.max(first[1],first[2]));
            while(true){
                if(first[2]==max1){
                    System.out.print("B ");
                    break;
                }
                if(first[0]==max1){
                    System.out.print("C ");
                    break;
                }
                System.out.print("J ");
                break;
            }

            int max2=Math.max(second[0],Math.max(second[1],second[2]));
            while(true){
                if(second[2]==max2){
                    System.out.print("B ");
                    break;
                }
                if(second[0]==max2){
                    System.out.print("C ");
                    break;
                }
                System.out.print("J ");
                break;
            }
            System.out.println();
        }
    }
}

