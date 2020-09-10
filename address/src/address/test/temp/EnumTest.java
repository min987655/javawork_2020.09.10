package address.test.temp;

import org.junit.Test;

import address.model.GroupType;
import address.model.Member;

public class EnumTest {

	@Test
	public void enum_toString_test() {
		Member m = new Member(1, "홍길동", "00000000000", "부산", GroupType.가족);
		System.out.println(m.getGroupType().toString());
	}
}
