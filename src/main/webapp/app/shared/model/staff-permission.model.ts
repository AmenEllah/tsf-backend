import { ActionPermission } from 'app/shared/model/enumerations/action-permission.model';
import { ScopePermission } from 'app/shared/model/enumerations/scope-permission.model';
import { StaffType } from 'app/shared/model/enumerations/staff-type.model';

export interface IStaffPermission {
  id?: number;
  idRole?: number;
  idBu?: number;
  action?: ActionPermission;
  scopePermission?: ScopePermission;
  staffType?: StaffType;
}

export class StaffPermission implements IStaffPermission {
  constructor(
    public id?: number,
    public idRole?: number,
    public idBu?: number,
    public action?: ActionPermission,
    public scopePermission?: ScopePermission,
    public staffType?: StaffType
  ) {}
}
