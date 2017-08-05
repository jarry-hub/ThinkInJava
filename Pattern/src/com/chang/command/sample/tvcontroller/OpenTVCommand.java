package com.chang.command.sample.tvcontroller;

public class OpenTVCommand implements Command {
	private ReceiverTV receiverTV = new ReceiverTV();
	
	public OpenTVCommand(ReceiverTV receiverTV) {
		this.receiverTV = receiverTV;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		receiverTV.openTV();
	}
}
