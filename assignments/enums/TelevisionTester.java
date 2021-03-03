public class TelevisionTester {
	public static void main(String[] args) {
		Television tv = new Television();
		for (Channel channel: Channel.values()) {
			tv.changeChannel(channel);
		}
	}
}