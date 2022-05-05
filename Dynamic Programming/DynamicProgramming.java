public class DynamicProgramming {

    // 1137 
    // Basic recursion
    public int tribonacci(int n) {
        if(n == 0) return 0;
        if(n == 1 || n == 2) return 1;
        
        return tribonacci(n-1) + tribonacci(n-2) + tribonacci(n-3) ; 
    }

    // Memoization
    public int tribonacci_memo(int n,int[] dp) {
        if(n == 0) return dp[n] = 0;
        if(n == 1 || n == 2) return dp[n] = 1;
        if(dp[n] != 0 ) return dp[n];

        return dp[n] = tribonacci_memo(n-1,dp) + tribonacci_memo(n-2,dp) + tribonacci_memo(n-3,dp) ; 
    }

    // Tabulation
    public int tribonacci_tab(int N,int[] dp) {
        for(int n = 0; n<=N; n++)
        { 
            if(n == 0) {
                dp[n] = 0;
                continue;
            }
            if(n == 1 || n == 2) {
                dp[n] = 1;
                continue;
            }
            dp[n] = dp[n-1] + dp[n-2]+ dp[n-3]; 
        }
        return dp[N];
    }

    // Optimization
    public int tribonacci_optimized(int N) {
        int a = 0;
        int b = 1;
        int c = 1;
        for(int n = 0; n<N; n++)
        { 
            int sum = a+b+c;
            a = b;
            b = c;
            c = sum;
        }
        return a;
    }


    // 70 
    // Basic
    public int climbStairs(int n) {
        if(n < 0) return 0;
        if( n == 0 || n == 1 ) return 1;
        return climbStairs(n-1)+climbStairs(n-2);
    }

    // Memoization
    public int climbStairs_memo(int n,int[] dp) {
        if(n < 0) return 0;
        if( n == 0 || n == 1 ) return dp[n] = 1;
        if(dp[n] != 0) return dp[n];
        return dp[n] = climbStairs(n-1)+climbStairs(n-2);
    }

    // Tabulation 
    public int climbStairs_tab(int N,int[] dp) {
        for(int n = 0; n <= N ; n++)
        {
            if( n == 0 || n == 1 ) {
                dp[n] = 1; 
                continue;
            }
            dp[n] = dp[n-1] + dp[n-2];
        }
        return dp[N];
    }

    // Optimization
    public int climbStairs_optimized(int N) {
        int a = 1;
        int b = 1;
        for(int n = 0; n < N ; n++)
        {
            int sum = a+b;
            a = b;
            b = sum;
        }
        return a;
    }


    // 746
    // Basic
    public int helper(int n, int[] cost){
        
        if(n<=1) return cost[n];
        
        int len = cost.length;
        int call1 = helper(n-1,cost);
        int call2 = helper(n-2,cost);
        int currCost = 0;
        if(n>=0 && n<len) currCost = cost[n];
        
        return currCost + Math.min(call1,call2);
    }    
    
    // Memoization
    public int helper_memo(int n, int[] cost,int[] dp){
        if(n<=1) return dp[n] = cost[n];
        
        if(dp[n]!=0) return dp[n];

        int len = cost.length;
        int call1 = helper_memo(n-1,cost,dp);
        int call2 = helper_memo(n-2,cost,dp);
        int currCost = 0;
        if(n>=0 && n<len) currCost = cost[n];
        
        return dp[n] = currCost + Math.min(call1,call2);
    }    

    // Tabulation
    public int helper_tab(int N, int[] cost,int[] dp){
        
        int n = 0;
        for(n = 0; n<N; n++){
            if(n<=1) {
                dp[n] = cost[n];
                continue;
            }            
            dp[n] = cost[n] + Math.min(dp[n-1],dp[n-2]);
        }
        dp[n] = Math.min(dp[n-1],dp[n-2]);
        return dp[N];
    }    

    // Optimized
    public int helper_optimized(int N, int[] cost){
        if(N<=1){
            return (N==0) ? 0 : cost[N];
        }
        int a = cost[0];
        int b = cost[1];
        int min = Math.min(a, b);
        for(int n = 2; n<N; n++){      
            min = cost[n] + Math.min(a,b);
            b = cost[n];
            a = cost[n-1];
        }
        return min;
    }    

    public int minCostClimbingStairs(int[] cost) {
        // return helper(cost.length,cost);
        int[] dp = new int[cost.length+1];
        return helper_memo(cost.length,cost,dp);
    }

    

    // Friends Pairings gfg
    // Basic
    public long countFriendsPairings(int n){ 
        if(n<=1){
            return n<0?0:1;
        }
        long call1 = countFriendsPairings(n-1);
        long call2 = countFriendsPairings(n-2);
        return call1+call2*(n-1);
    
    }

    // Memoization
    public long countFriendsPairings_memo(int n,long[] dp){ 
        if(n<=1){
            return n<0?0:(dp[n]=1);
        }

        if(dp[n] != 0) return dp[n];

        long call1 = countFriendsPairings_memo(n-1,dp);
        long call2 = countFriendsPairings_memo(n-2,dp);
        return dp[n] = call1+call2*(n-1);
    }

    // Tabulation
    public long countFriendsPairings_tab(int N) 
    { 
        long[] dp = new long[N+1];
        for (int n = 0; n <=N; n++) {
            if(n<=1){
                dp[n]=1;
                continue;
            }

            dp[n] = dp[n-1]+dp[n-2]*(n-1);
        }
        return dp[N];
    }

    // Optimized
    public long countFriendsPairings_optimized(int N) 
    { 
        long a = 1;
        long b = 1;
        for (int n = 2; n <=N; n++) {
            long ans = (long)((b + a*(n-1))%(1e9+7));
            a = b;
            b = ans;
        }   
        return b;
    }


    
}
