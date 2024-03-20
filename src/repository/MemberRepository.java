package repository;

import Model.Book;
import Model.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MemberRepository {
    private static List<Member> membersOfLibrary;
    private MemberRepository() {
        membersOfLibrary = new ArrayList<>();
    }
    private static class Holder {
        private static MemberRepository INSTANCE = new MemberRepository();
    }

    public static MemberRepository getInstance() {
        return MemberRepository.Holder.INSTANCE;
    }

    public static void addMember(Member member) {
        membersOfLibrary.add(member);
    }

    public static List<Member> getMembersOfLibrary() {
        return membersOfLibrary;
    }

    public static void removeMember(Member member) {
        membersOfLibrary.remove(member);
    }

}
