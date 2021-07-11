export interface INationality {
  id?: number;
  libelleFR?: string;
  libelleEN?: string;
  code?: string;
  flag?: string;
}

export class Nationality implements INationality {
  constructor(public id?: number, public libelleFR?: string, public libelleEN?: string, public code?: string, public flag?: string) {}
}
