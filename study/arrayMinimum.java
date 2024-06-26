// LeetCode 
// 2974. Minimum Number Game
// 최소 숫자 게임

import java.util.Arrays;

class Game {
    public static int[] playGame(int[] nums) {
        // nums 배열을 정렬
        Arrays.sort(nums);
        int n = nums.length;
        int[] arr = new int[n];
        
        int index = 0;
        int left = 0;
        int right = n - 1;
        
        while (left < right) {
            // 앨리스가 가장 작은 요소를 제거
            int alice = nums[left];
            left++;
            
            // 밥이 그 다음 작은 요소를 제거
            int bob = nums[left];
            left++;
            
            // 밥이 제거한 요소를 arr에 추가
            arr[index] = bob;
            index++;
            
            // 앨리스가 제거한 요소를 arr에 추가
            arr[index] = alice;
            index++;
        }
        
        return arr;
    }
    }
