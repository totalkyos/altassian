package com.hazelcast.cluster.impl.operations;

import com.hazelcast.instance.Capability;

import java.util.Set;

public class MemberCapabilityUpdateRequestOperation extends MemberCapabilityChangedOperation {

    public MemberCapabilityUpdateRequestOperation() {
    }

    public MemberCapabilityUpdateRequestOperation(String uuid, Set<Capability> capabilities){
        super(uuid, capabilities);
    }
}
