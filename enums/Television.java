public class Television {
	
	public Television() {
	}

	public void changeChannel(Channel channel) {
		switch (channel) {
		case CBS:
			System.out.println("You changed to the CBS channel");
			break;
		case ABC:
			System.out.println("You changed to the ABC channel");
			break;
		case MTV:
			System.out.println("You changed to the MTV channel");
			break;
		case TBS:
			System.out.println("You changed to the TBS channel");
			break;
		case HBO:
			System.out.println("You changed to the HBO channel");
			break;
		case ESPN:
			System.out.println("You changed to the ESPN channel");
			break;
		}
	}
}