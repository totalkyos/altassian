package com.hazelcast.instance;

/**
 * Defines the different roles a member can have in the cluster
 * A member with no roles has read and write access to the data in the cluster
 * @since 3.3-atlassian-1
 */
public enum MemberRole {
    /**
     * The EXECUTOR role allows a member receive application tasks
     * that have been scheduled for execution in the cluster
     */
    EXECUTOR(0),
    /**
     * The PARTITION_HOST role makes a member eligible to host data partitions / replicas
     */
    PARTITION_HOST(1);

    private final int id;

    MemberRole(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static MemberRole valueOf(int id) {
        for (MemberRole role : values()) {
            if (role.id == id) {
                return role;
            }
        }
        throw new IllegalArgumentException(String.format("No MemberRole with id [%d] could be found", id));
    }
}
