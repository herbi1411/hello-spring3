package hello.hellospring3.MemberService;

import hello.hellospring3.domain.Member;
import hello.hellospring3.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemoryMemberRepository memberRepository;
    
    public MemberService(MemoryMemberRepository memoryMemberRepository) {
        this.memberRepository = memoryMemberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
    
    public void clearStore() {
        memberRepository.clearStore();
    }
}
