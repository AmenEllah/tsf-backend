export interface IFAQ {
  id?: number;
  question?: string;
  answer?: string;
}

export class FAQ implements IFAQ {
  constructor(public id?: number, public question?: string, public answer?: string) {}
}
