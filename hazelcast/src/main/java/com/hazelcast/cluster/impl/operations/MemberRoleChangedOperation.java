package com.hazelcast.cluster.impl.operations;

import com.hazelcast.cluster.impl.ClusterServiceImpl;
import com.hazelcast.instance.MemberRole;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;

import java.io.IOException;
import java.util.Set;

public class MemberRoleChangedOperation extends AbstractClusterOperation {


    private String uuid;
    private Set<MemberRole> roles;

    public MemberRoleChangedOperation() {
    }

    public MemberRoleChangedOperation(String uuid, Set<MemberRole> roles){
        this.uuid = uuid;
        this.roles = roles;
    }

    @Override
    public void run() throws Exception {
        ((ClusterServiceImpl) getService()).updateMemberRoles(uuid, roles);
    }

    @Override
    protected void writeInternal(ObjectDataOutput out) throws IOException {
        super.writeInternal(out);
        out.writeUTF(uuid);
        MemberRole.writeRoles(out, roles);
    }

    @Override
    protected void readInternal(ObjectDataInput in) throws IOException {
        super.readInternal(in);
        uuid = in.readUTF();

        roles = MemberRole.readRoles(in);
    }
}
