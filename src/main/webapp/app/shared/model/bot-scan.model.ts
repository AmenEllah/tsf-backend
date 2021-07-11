export interface IBotScan {
  id?: number;
  ref_demande?: string;
  cliDelta?: string;
  signature?: string;
  compte?: string;
}

export class BotScan implements IBotScan {
  constructor(
    public id?: number,
    public ref_demande?: string,
    public cliDelta?: string,
    public signature?: string,
    public compte?: string
  ) {}
}
