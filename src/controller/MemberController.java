package controller;

import Model.Member;
import repository.MemberRepository;
import Exception.LMSException;

import java.util.UUID;

import static Exception.ExceptionName.*;

public class MemberController {
    private MemberRepository memberRepository;

    public MemberController() {
        memberRepository = MemberRepository.getInstance();
    }

    public void registerMember(String name,String email,int phone) throws LMSException{
        validatePhone(phone);
        validateEmail(email);
        memberRepository.addMember(new Member(name,email,phone));
    }

    public void registerMember(Member member) throws LMSException{
        validatePhone(member.getPhone());
        validateEmail(member.getEmail());
        memberRepository.addMember(member);
    }
    public void cancelMembership(UUID id) throws LMSException{
        for(var member:memberRepository.getMembersOfLibrary()) {
            if(member.getId().equals(id)) {
                memberRepository.removeMember(member);
            }
        }
        throw new LMSException(NoMemberException,"No member with this id exists");
    }
    private void validatePhone(int phone) throws LMSException{
        for(var member:memberRepository.getMembersOfLibrary()) {
            if (member.getPhone() == phone) {
                throw new LMSException(DuplicatePhoneNumberException, "A user with this phone number already exists");
            }
        }
    }
    private void validateEmail(String email) throws LMSException{
        for(var member:memberRepository.getMembersOfLibrary()) {
            if(member.getEmail().equals(email)) {
                throw new LMSException(DuplicateEmailException,"A user with this email already exists");
            }
        }
    }
}
