package org.lyg.common;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@Provider
public class RestExceptionMapper implements ExceptionMapper<Exception> {

    private static Logger logger = LogManager.getLogger(RestExceptionMapper.class.getName());

    @Override
    public Response toResponse(Exception exception) {
        logger.info("Catch Exception = " + exception);
        if (exception instanceof MiniException) {
            return Response.status(Status.OK).entity(exception).build();
        }

        MiniException error = new MiniException(MiniConstant.ERRORCODE_ER1001, ErrorCodeEnum.ER1001.getMsg());
        return Response.status(Status.OK).entity(error).build();
    }

}