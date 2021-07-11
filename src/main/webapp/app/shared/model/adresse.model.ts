export interface IAdresse {
  id?: number;
  rue?: string;
  codePostal?: number;
  ville?: string;
  pays?: string;
}

export class Adresse implements IAdresse {
  constructor(public id?: number, public rue?: string, public codePostal?: number, public ville?: string, public pays?: string) {}
}
