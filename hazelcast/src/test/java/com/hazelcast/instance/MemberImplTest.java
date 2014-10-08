package com.hazelcast.instance;

import com.hazelcast.nio.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.EnumSet;
import java.util.Set;
import java.util.UUID;

import static com.hazelcast.instance.Capability.EXECUTOR;
import static com.hazelcast.instance.Capability.PARTITION_HOST;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class MemberImplTest {

    private static final String MEMBER_UUID = UUID.randomUUID().toString();

    @Test
    public void testMemberCapability() throws Exception {
        MemberImpl allCapabilitiesMember = createMember(EnumSet.allOf(Capability.class));
        assertTrue(allCapabilitiesMember.hasCapability(EXECUTOR));
        assertTrue(allCapabilitiesMember.hasCapability(PARTITION_HOST));

        MemberImpl executorMember = createMember(EnumSet.of(PARTITION_HOST));
        assertTrue(executorMember.hasCapability(EXECUTOR));
        assertFalse(executorMember.hasCapability(PARTITION_HOST));

        MemberImpl partitionHostMember = createMember(EnumSet.of(PARTITION_HOST));
        assertFalse(partitionHostMember.hasCapability(EXECUTOR));
        assertTrue(partitionHostMember.hasCapability(PARTITION_HOST));

        MemberImpl noCapabilityMember = createMember(EnumSet.noneOf(Capability.class));
        assertFalse(noCapabilityMember.hasCapability(EXECUTOR));
        assertFalse(noCapabilityMember.hasCapability(PARTITION_HOST));
    }


    private MemberImpl createMember(Set<Capability> capabilities) throws Exception {
        return new MemberImpl(new Address("10.0.0.1", 5701), true, MEMBER_UUID, null, capabilities, null);
    }
}
