package com.orangeleap.donatenow.service;

import java.util.List;
import com.orangeleap.donatenow.domain.CustomEntity;
import javax.servlet.http.HttpServletRequest;
import com.orangeleap.client.CustomTableRow;

public interface CustomEntityService {
  public List<CustomEntity> getCustomEntity(String guid);
  public CustomTableRow CreateCustomTableRow(String guid, HttpServletRequest request);
}