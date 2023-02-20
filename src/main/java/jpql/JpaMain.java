package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // Member member = new Member();
            // member.setUsername("member1");
            // member.setAge(10);
            // em.persist(member);
            //
            // em.flush();
            // em.clear();

            /**
             * TypedQuery, Query 테스트
             */
            // // 반환 타입이 명확할 때 사용
            // TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);
            // TypedQuery<String> query2 = em.createQuery("select m.username, m.age from Member m", String.class);
            //
            // // 반환 타입이 명확하지 않을 때 사용
            // Query query3 = em.createQuery("select m.username, m.age from Member m", Member.class);

            /**
             * 결과 조회 API 테스트
             */
            // TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
            // // 값이 여러 개일 때 (결과가 없으면 빈 리스트 반환)
            // List<Member> resultList = query.getResultList();

            // // 값이 하나 일 때 (결과가 없거나 2개 이상이면 에러 발생) => Spring Data JPA는 null이나 Optional 반환
            // // 바인딩은 파라미터 명 기준, 위치 기준 2가지 방법을 제공하는데, 웬만하면 파라미터 명 기준을 사용하자
            // Member result = em.createQuery("select m from Member m where m.username = :username", Member.class)
            //         .setParameter("username", "member1")
            //         .getSingleResult();
            // System.out.println("singleResult = " + result.getUsername());

            /**
             * 프로젝션 테스트 (select 절에 조회할 대상을 지정하는 것)
             */
            // // 여기서 가져오는 Member(엔티티)는 영속성 컨텍스트에서 관리하게 됨
            // List<Member> result = em.createQuery("select m from Member m", Member.class)
            //         .getResultList();

            // // Member와 Team Join 쿼리가 나감 (아래의 코드는 Join이 되는지 모호하기 때문에, 권장되지 않는 방법)
            // List<Team> result = em.createQuery("select  m.team from Member m", Team.class)
            //         .getResultList();
            // // 따라서, 명시적으로 join이 되는지 알 수 있게 아래의 방법이 권장됨
            // List<Team> result = em.createQuery("select  m.team from Member m join m.team", Team.class)
            //         .getResultList();

            // // 임베디드 타입 프로젝션 (주의할 점은 임베디드 타입 자체를 조회하면 안되고, 엔티티를 기준으로 조회해야 함)
            // em.createQuery("select o.address from Order o", Address.class)
            //         .getResultList();

            // 스칼라 타입 프로젝션
            // // Object[] 타입으로 조회
            // List resultList = em.createQuery("select distinct m.username, m.age from Member m")
            //         .getResultList();
            //
            // Object o = resultList.get(0);
            // Object[] result = (Object[]) o;
            //
            // System.out.println("result[0] = " + result[0]);
            // System.out.println("result[1] = " + result[1]);

            // // Object 타입 캐스팅 생략
            // List<Object[]> resultList = em.createQuery("select distinct m.username, m.age from Member m")
            //         .getResultList();
            // Object[] result = resultList.get(0);
            //
            // System.out.println("result[0] = " + result[0]);
            // System.out.println("result[1] = " + result[1]);

            // // new 명령어로 조회
            // List<MemberDTO> result = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
            //         .getResultList();
            //
            // MemberDTO memberDTO = result.get(0);
            //
            // System.out.println("memberDTO.getUsername() = " + memberDTO.getUsername());
            // System.out.println("memberDTO.getAge() = " + memberDTO.getAge());

            /**
             * 페이징 테스트
             */
            // for (int i = 0; i < 100; i++) {
            //     Member member = new Member();
            //     member.setUsername("member" + i);
            //     member.setAge(i);
            //     em.persist(member);
            // }
            //
            // em.flush();
            // em.clear();
            //
            // List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class)
            //         .setFirstResult(1)
            //         .setMaxResults(10)
            //         .getResultList();
            // System.out.println("result.size() = " + result.size());
            // for (Member member1 : result) {
            //     System.out.println("member1 = " + member1);
            // }

            // Team team = new Team();
            // team.setName("teamA");
            // em.persist(team);
            //
            // Member member = new Member();
            // member.setUsername("member1");
            // member.setAge(10);
            // member.setMemberType(MemberType.ADMIN);
            //
            // member.setTeam(team);
            //
            // em.persist(member);
            //
            // em.flush();
            // em.clear();

            /**
             * 조인 테스트
             */
            // String query = "select m from Member m inner join m.team t"; // 내부 조인
            // String query = "select m from Member m left outer join m.team t"; // 외부 조인
            // String query = "select m from Member m, Team t where m.username = t.name"; // 세타 조인
            // String query = "select m from Member m left join m.team t where t.name = 'teamA'"; // 조인 대상 필터링
            // String query = "select m from Member m left join Team t on m.username = t.name "; // 연관 관계 없는 외부 조인

            // List<Member> result = em.createQuery(query, Member.class)
            //         .getResultList();

            /**
             * JPA 타입 표현 테스트
             */
            // String query = "select m.username, 'HELLO', TRUE from Member m " +
            //                 "where m.memberType = :userType";
            //
            // List<Object[]> result = em.createQuery(query)
            //         .setParameter("userType", MemberType.ADMIN)
            //         .getResultList();
            //
            // for (Object[] objects : result) {
            //     System.out.println("objects[0] = " + objects[0]);
            //     System.out.println("objects[1] = " + objects[1]);
            //     System.out.println("objects[2] = " + objects[2]);
            // }

            /**
             * case(조건)식 테스트
             */
            // String query = "select " +
            //                     "case when m.age <= 10 then '학생요금' " +
            //                          "when m.age >= 60 then '경로요금' " +
            //                          "else '일반요금' " +
            //                     "end " +
            //                 "from Member m";

            // String query = "select coalesce(m.username, '이름 없는 회원') as username from Member m ";
            // String query = "select nullif(m.username, '관리자') as username from Member m ";

            /**
             * JPQL 기본 함수
             */
            Member member1 = new Member();
            member1.setUsername("관리자1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("관리자1");
            em.persist(member2);

            em.flush();
            em.clear();

            // String query = "select concat('a', 'b') from Member m";
            // String query = "select substring(m.username, 2, 3) from Member m";
            // String query = "select locate('de', 'abcde') from Member m"; // 4(Integer) return
            // String query = "select size(t.members) from Team t"; // collection size return
            // String query = "select index(t.members) from Team t"; // collection 위치 값을 구할 때
            String query = "select function('group_concat', m.username) from Member m";

            List<String> result = em.createQuery(query, String.class)
                    .getResultList();

            for (String s : result) {
                System.out.println("s = " + s);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}
