import { Moment } from 'moment';
import { appelVisioStatus } from 'app/shared/model/enumerations/appel-visio-status.model';
import { withCertifStatus } from 'app/shared/model/enumerations/with-certif-status.model';
import { withSignatureStatus } from 'app/shared/model/enumerations/with-signature-status.model';

export interface ISubscriberStatus {
  id?: number;
  email?: string;
  numCin?: string;
  withAppelVisio?: appelVisioStatus;
  withCertif?: withCertifStatus;
  withSignature?: withSignatureStatus;
  dateAppelVisio?: Moment;
}

export class SubscriberStatus implements ISubscriberStatus {
  constructor(
    public id?: number,
    public email?: string,
    public numCin?: string,
    public withAppelVisio?: appelVisioStatus,
    public withCertif?: withCertifStatus,
    public withSignature?: withSignatureStatus,
    public dateAppelVisio?: Moment
  ) {}
}
