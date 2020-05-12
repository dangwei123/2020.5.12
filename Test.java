不重复打印数组中相加和为给定值的所有二元组或三元组
public class PrintUniquePair {
    public static void main(String[] args) {
        int[] arr={1,1,1,2,8,9,9,9,9};
        printUniqueTwo(arr,10);
    }
    //二元组
    public static void printUniqueTwo(int[] arr, int target){
        int left=0;
        int right=arr.length-1;
        while(left<right){
            int sum=arr[left]+arr[right];
            if(sum==target){
                if((left==0||arr[left]!=arr[left-1])&&(right==arr.length-1||arr[right]!=arr[right+1])){
                    System.out.println(arr[left]+" "+arr[right]);
                    left++;
                    right--;
                }
            }else if(sum<target){
                left++;
            }else{
                right--;
            }
        }
    }

    //三元组
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        int n=nums.length;
        if(n==0) return res;
        Arrays.sort(nums);
        for(int i=0;i<n-2;i++){
            if(nums[i]>0) break;
            if(i>0&&nums[i]==nums[i-1]) continue;
            int left=i+1;
            int right=n-1;
            while(left<right){
                int sum=nums[left]+nums[right];
                if(sum==-nums[i]){
                    res.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    left++;
                    while(left<right&&left>0&&nums[left]==nums[left-1]) left++;
                    right--;
                    while(left<right&&right!=n-1&&nums[right]==nums[right+1]) right--;
                }else if(sum+nums[i]<0){
                    left++;
                    while(left<right&&left>0&&nums[left]==nums[left-1]) left++;
                }else{
                    right--;
                    while(left<right&&right!=n-1&&nums[right]==nums[right+1]) right--;
                }
            }
        }
        return res;
    }
}


累加和为给定值的最长连续子数组
public class GetMaxLen {

    //滑动窗口
    public int getlen(int[] arr,int target){
        int left=0;
        int max=0;
        int sum=0;
        for(int right=0;right<arr.length;right++){
            sum+=arr[right];
            while(sum>=target){
                if(sum==target){
                    max=Math.max(max,right-left+1);
                }
                sum-=arr[left++];
            }
        }
        return max;
    }
	
	//前缀和
    public int getMaxLen(int[] arr,int k){
        Map<Integer,Integer> map=new HashMap<>();
        map.put(0,-1);
        int max=0;
        int sum=0;
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
            if(map.containsKey(sum-k)){
                max=Math.max(max,i-map.get(sum-k));
            }
            if(!map.containsKey(sum)){
                map.put(sum,i);
            }
        }
        return max;
    }
    
    
    //和为k的连续子数组个数
    public int subarraySum(int[] nums, int k) {
        Map<Integer,Integer> map=new HashMap<>();
        map.put(0,1);
        int res=0;
        int pre=0;
        for(int i=0;i<nums.length;i++){
            pre+=nums[i];
            int cur=pre-k;
            if(map.containsKey(cur)){
                res+=map.get(cur);
            }
            map.put(pre,map.getOrDefault(pre,0)+1);
        }
        return res;
    }
}

未排序数组中小于给定值的最长连续子数组
public class GetMaxLenLess {
    public int getMaxLen(int[] arr,int target){
        int n=arr.length;
        int[] sumArr=new int[n+1];
        int sum=0;
        for(int i=0;i<n;i++){
            sum+=arr[i];
            sumArr[i+1]=Math.max(sum,sumArr[i]);
        }

        int res=0;
        sum=0;
        for(int i=0;i<n;i++){
            sum+=arr[i];
            int tmp=sum-target;
            int left=0;
            int right=n;
            while(left<right){
                int mid=left+(right-left)/2;
                if(sumArr[mid]<tmp){
                    left=mid+1;
                }else{
                    right=mid;
                }
            }
            int len=0;
            if(sumArr[left]>=tmp) len=i-left+1;
            res=Math.max(res,len);
        }
        return res;
    }
}


计算右侧小于当前元素的个数
public class CountSmaller {
    private int[] res;
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> list=new ArrayList<>();
        int n=nums.length;
        res=new int[n];
        int[] index=new int[n];
        for(int i=0;i<n;i++){
            index[i]=i;
        }
        mergeSort(nums,index,0,n-1);
        for(int i=0;i<n;i++){
            list.add(res[i]);
        }
        return list;
    }
    private void mergeSort(int[] nums,int[] index,int left,int right){
        if(left>=right) return;
        int mid=left+(right-left)/2;
        mergeSort(nums,index,left,mid);
        mergeSort(nums,index,mid+1,right);
        merge(nums,index,left,mid,right);
    }
    private void merge(int[] nums,int[] index,int left,int mid,int right){
        int i=left;
        int j=mid+1;
        int len=right-left+1;
        int[] tmp=new int[len];
        int k=0;
        while(i<=mid&&j<=right){
            if(nums[index[i]]<=nums[index[j]]){
                res[index[i]]+=(j-mid-1);
                tmp[k++]=index[i++];
            }else{
                tmp[k++]=index[j++];
            }
        }
        while(i<=mid){
            res[index[i]]+=right-mid;
            tmp[k++]=index[i++];
        }
        while(j<=right){
            tmp[k++]=index[j++];
        }
        System.arraycopy(tmp,0,index,left,len);
    }
}

自然数组排序
public class Sort {
    public void sort1(int[] arr){
        for(int i=0;i<arr.length;i++){
            while(arr[i]!=i+1){
                int tmp=arr[i];
                arr[i]=arr[arr[i]-1];
                arr[arr[i]-1]=tmp;
            }
        }
    }
}





