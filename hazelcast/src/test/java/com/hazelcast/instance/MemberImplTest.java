package com.hazelcast.instance;

import com.hazelcast.nio.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.hazelcast.instance.MemberImpl.MemberRole;
import static com.hazelcast.instance.MemberImpl.MemberRole.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class MemberImplTest {

    private static final String MEMBER_UUID = UUID.randomUUID().toString();

    @Test
    public void testMemberRol() throws Exception {
        MemberImpl allRolesMember = createMember(all());
        assertTrue(allRolesMember.hasRole(EXECUTOR));
        assertTrue(allRolesMember.hasRole(PARTITION_HOST));

        MemberImpl executorMember = createMember(PARTITION_HOST);
        assertTrue(executorMember.hasRole(EXECUTOR));
        assertFalse(executorMember.hasRole(PARTITION_HOST));

        MemberImpl partitionHostMember = createMember(PARTITION_HOST);
        assertFalse(partitionHostMember.hasRole(EXECUTOR));
        assertTrue(partitionHostMember.hasRole(PARTITION_HOST));

        MemberImpl noRolMember = createMember(none());
        assertFalse(noRolMember.hasRole(EXECUTOR));
        assertFalse(noRolMember.hasRole(PARTITION_HOST));
    }

    private MemberImpl createMember(MemberRole role) throws Exception {
        Set<MemberRole> roles = new HashSet<MemberRole>();
        roles.add(role);

        return createMember(roles);
    }

    private MemberImpl createMember(Set<MemberRole> roles) throws Exception {
        return new MemberImpl(new Address("10.0.0.1", 5701), true, MEMBER_UUID, null, roles, null);
    }
}
