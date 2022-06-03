package user


import spock.lang.Specification

class UserRegisterSpockTest extends Specification {

    def "Mock 테스트"() {
        given:
        WeakPasswordChecker mockPasswordChecker = Mock(WeakPasswordChecker.class);
        MemoryUserRepository fakeRepository = new MemoryUserRepository();
        EmailNotifier mockEmailNotifier = Mock(EmailNotifier.class);
        UserRegister userRegister = new UserRegister(mockPasswordChecker, fakeRepository, mockEmailNotifier);

        when:
        userRegister.register("id", "pw", "email")

        then:
        1 * mockPasswordChecker.checkPasswordWeak(_) // 행위 검증
    }

    def "Stub 테스트"() {
        given:
        WeakPasswordChecker mockPasswordChecker = Stub(WeakPasswordChecker.class);
        MemoryUserRepository fakeRepository = new MemoryUserRepository();
        EmailNotifier mockEmailNotifier = Mock(EmailNotifier.class);
        UserRegister userRegister = new UserRegister(mockPasswordChecker, fakeRepository, mockEmailNotifier);

        when:
        userRegister.register("id", "pw", "email")

        then:
        1 * mockPasswordChecker.checkPasswordWeak(_)
        // 'turn the stub into a mock' 메시지와 함께 InvalidSpecException 발생. Stub으로는 행위 검증이 불가능하다.
    }
}
