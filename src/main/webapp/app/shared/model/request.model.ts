import { Moment } from 'moment';
import { IRequestBankAccount } from 'app/shared/model/request-bank-account.model';
import { StateRequest } from 'app/shared/model/enumerations/state-request.model';
import { RequestStatus } from 'app/shared/model/enumerations/request-status.model';

export interface IRequest {
  id?: number;
  visioDate?: Moment;
  sendingMailDate?: Moment;
  state?: boolean;
  step?: string;
  codeVerification?: string;
  amplitudeRef?: number;
  requestState?: StateRequest;
  requestStatus?: RequestStatus;
  offerId?: number;
  personalInfoId?: number;
  documentId?: number;
  requestBankAccounts?: IRequestBankAccount[];
}

export class Request implements IRequest {
  constructor(
    public id?: number,
    public visioDate?: Moment,
    public sendingMailDate?: Moment,
    public state?: boolean,
    public step?: string,
    public codeVerification?: string,
    public amplitudeRef?: number,
    public requestState?: StateRequest,
    public requestStatus?: RequestStatus,
    public offerId?: number,
    public personalInfoId?: number,
    public documentId?: number,
    public requestBankAccounts?: IRequestBankAccount[]
  ) {
    this.state = this.state || false;
  }
}
