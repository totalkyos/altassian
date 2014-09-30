package com.hazelcast.cluster;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static com.hazelcast.instance.MemberImpl.MemberRole;

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
        out.writeInt(roles.size());
        for (MemberRole role : roles) {
            out.writeUTF(role.name());
        }
    }

    @Override
    protected void readInternal(ObjectDataInput in) throws IOException {
        super.readInternal(in);
        uuid = in.readUTF();

        roles = new HashSet<MemberRole>();

        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            roles.add(MemberRole.valueOf(in.readUTF()));
        }
    }
}
