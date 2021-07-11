import { Moment } from 'moment';
import { IRequest } from 'app/shared/model/request.model';
import { DocStatus } from 'app/shared/model/enumerations/doc-status.model';

export interface IReqDocUpload {
  id?: number;
  pathDoc?: string;
  typeDoc?: string;
  uploadedAt?: Moment;
  updatedAt?: Moment;
  docStatus?: DocStatus;
  request?: IRequest;
}

export class ReqDocUpload implements IReqDocUpload {
  constructor(
    public id?: number,
    public pathDoc?: string,
    public typeDoc?: string,
    public uploadedAt?: Moment,
    public updatedAt?: Moment,
    public docStatus?: DocStatus,
    public request?: IRequest
  ) {}
}
