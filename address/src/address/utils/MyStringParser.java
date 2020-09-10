package address.utils;

public class MyStringParser { // 오브젝트 아님. 걍 클래스(존재X)
	
	public static int getId(String selectedList) {
		// .은 파싱 안됨 파싱하고 싶으면 \\. 이나 [.] 쓰기
		return Integer.parseInt(selectedList.split("\\.")[0]); 
	}
}
