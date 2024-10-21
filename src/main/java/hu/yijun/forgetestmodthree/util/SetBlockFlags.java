package hu.yijun.forgetestmodthree.util;

public class SetBlockFlags {

    public static final int BLOCK_UPDATE = 1;
    public static final int SEND_TO_CLIENT = 2;
    public static final int DONT_RERENDER = 4;
    public static final int FORCE_MAIN_THREAD_RERENDER = 8;
    public static final int PREVENT_NEIGHBOUR_UPDATE = 16;
    public static final int PREVENT_NEIGHBOUR_REACTION_DROPPING = 32;
    public static final int BLOCK_MOVED = 64;
}
